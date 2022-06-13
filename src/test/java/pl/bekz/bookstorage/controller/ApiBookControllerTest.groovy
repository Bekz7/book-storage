package pl.bekz.bookstorage.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import pl.bekz.bookstorage.entity.Book
import spock.lang.Specification


@AutoConfigureMockMvc
@WebMvcTest
@SpringBootTest
class ApiBookControllerTestApiBookControllerTest extends Specification {
    @Autowired
    private MockMvc mvc

    def "Positive adding scenario"() {
        given:
        Book book = new Book(0, "Wrong Name", "title", "ISBN")
        when:
        ResultActions addBook = mvc.perform(MockMvcRequestBuilders.post("/api/addBook", book))
        then:
        addBook.andExpect(MockMvcResultMatchers.status().isOk())
    }

    def "Should get all books"() {
        expect:
        mvc.perform(MockMvcRequestBuilders.get("/api/getAll"))
                .andExpect(MockMvcResultMatchers.status().isOk())
    }
}
