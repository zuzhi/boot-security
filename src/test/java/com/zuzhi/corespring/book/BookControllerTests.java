package com.zuzhi.corespring.book;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * BookControllerTests
 *
 * @author zuzhi
 * @date 2018/5/10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookControllerTests {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Autowired
    private BookService bookService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        bookService.save(new Book("On Lisp", "Paul Graham"));
    }

    @Test
    public void testFindAllBooks_thenIsOk() throws Exception {
        mockMvc.perform(get("/api/v1/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("On Lisp")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testFindBookById_thenIsOk() throws Exception {
        mockMvc.perform(get("/api/v1/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().string(containsString("Paul Graham")));
    }

    @Test
    public void testSaveBook_thenIsCreated() throws Exception {
        mockMvc.perform(post("/api/v1/books")
                .with(httpBasic("user", "pass"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(new Book("Refactoring", "Martin Fowler")))
        )
                .andExpect(status().isCreated());
    }

    @Test
    public void testUpdateBookByPut_thenIsOk() throws Exception {
        mockMvc.perform(put("/api/v1/books/1")
                .with(httpBasic("user", "pass"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(new Book(1L, "ANSI Common Lisp", "Paul Graham")))
        )
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateBookByPatch_thenIsOk() throws Exception {
        mockMvc.perform(patch("/api/v1/books/1")
                .with(httpBasic("user", "pass"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(new HashMap<String, String>() {{
                    put("title", "Hackers and Painters");
                }}))
        )
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteBookById_thenIsNoContent() throws Exception {
        // Because delete original data can interfere with other tests, here I create a new book and get its id
        bookService.save(new Book("Code", "Charles Petzold"));
        List<Book> books = bookService.findByAuthor("Charles Petzold");

        mockMvc.perform(delete("/api/v1/books/" + books.get(0).getId())
                .with(httpBasic("user", "pass"))
        )
                .andExpect(status().isNoContent());
    }

    @Test
    public void testFindById_thenIsNotFound() throws Exception {
        mockMvc.perform(get("/api/v1/books/100"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testSaveBook_thenIsUnauthorized() throws Exception {
        mockMvc.perform(post("/api/v1/books"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testSaveBook_thenIsUnsupportedMediaType() throws Exception {
        mockMvc.perform(post("/api/v1/books")
                .with(httpBasic("user", "pass"))
        )
                .andExpect(status().isUnsupportedMediaType());
    }

    @Test
    public void testSaveBook_thenIsBadRequest() throws Exception {
        mockMvc.perform(post("/api/v1/books")
                .with(httpBasic("user", "pass"))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(new Book()))
        )
                .andExpect(status().isBadRequest());
    }

//    @Test
//    public void testUpdateBookByPut_thenIsBadRequest() throws Exception {
//        mockMvc.perform(put("/api/v1/books/1")
//                .with(httpBasic("user", "pass"))
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(JSON.toJSONString(new Book(2L, "UML Distilled", "Martin Fowler")))
//        )
//                .andExpect(status().isBadRequest());
//    }
}
