package pl.bekz.bookstorage

import groovy.json.JsonOutput
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import pl.bekz.bookstorage.entity.Book
import spock.lang.Specification

@SpringBootTest
@AutoConfigureMockMvc
@EnableAutoConfiguration
class AcceptanceSpec extends Specification {

    @Autowired
    private MockMvc mvc

    def "User should be abe to add book while proper value provided"() {
        given: "I create a book with proper values"
        Book book = new Book("Arong Aame", "title", "ISBN")
        def json = JsonOutput.toJson(book)

        when: "I want to store a book"
        ResultActions addBook = mvc.perform(MockMvcRequestBuilders.post("/api/addBook", json)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
        then: "I should be abe to do this"
        addBook.andExpect(MockMvcResultMatchers.status().isOk())

        when: "When I ask for all books"
        def getAllBooks = mvc.perform(MockMvcRequestBuilders.get("/api/getAll"))

        then: "I should see just added"
        getAllBooks.andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .response
                .contentAsString.contains(book.author)
    }

    def "User shouldn't be able to add book while wrong value provided"() {
        given: "I create a book with wrong author name"
        def wrongAuthorName = "Wrong Name"
        Book book = new Book(0, wrongAuthorName, "title", "ISBN")
        def bookInJsonType = JsonOutput.toJson(book)

        when: "I want to store a book"
        ResultActions addBook = mvc.perform(MockMvcRequestBuilders.post("/api/addBook", bookInJsonType)
                .content(bookInJsonType)
                .contentType(MediaType.APPLICATION_JSON))

        then: "Should receive bad request in response"
        addBook.andExpect(MockMvcResultMatchers.status().is4xxClientError())
    }
}
