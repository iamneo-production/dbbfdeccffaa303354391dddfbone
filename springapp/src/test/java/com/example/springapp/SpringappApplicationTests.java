package com.example.springapp;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class) 
@SpringBootTest(classes = SpringappApplication.class)
@AutoConfigureMockMvc
class SpringappApplicationTests {
	
	@Autowired

    private MockMvc mockMvc;    




    //Add A New Car

    @Test

    public void test_case1() throws Exception {

       

        String dataOne = "{\"carId\":\"12212\",\"carModel\":\"baleno\",\"carNo\":\"TN 38 CJ 6636\",\"status\":\"available\"}";

        mockMvc.perform(MockMvcRequestBuilders.post("/saveCar")

                .contentType(MediaType.APPLICATION_JSON)

                .content(dataOne)

                .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())

                .andReturn();

       

    }

   

   

    //Get All Car

    @Test

    public void test_case2() throws Exception {

       

        mockMvc.perform(MockMvcRequestBuilders.get("/getCars")

                .contentType(MediaType.APPLICATION_JSON)

                .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())

                .andExpect(MockMvcResultMatchers.jsonPath("$[*].carNo").exists())

                .andExpect(MockMvcResultMatchers.jsonPath("$").isNotEmpty())

                .andReturn();

       

    }

   

    //Get A Car By ID

    @Test

    public void test_case3() throws Exception {

       

        mockMvc.perform(MockMvcRequestBuilders.get("/getCar")

                .param("carId","12212")

                .contentType(MediaType.APPLICATION_JSON)

                .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.carModel").value("baleno"))

                .andExpect(jsonPath("$.carNo").value("TN 38 CJ 6636"))

                .andExpect(jsonPath("$.status").value("available"))

                .andReturn();

           

    }

   

   

    //Delete A Car

    @Test

    public void test_case4() throws Exception {

       

        mockMvc.perform(MockMvcRequestBuilders.get("/deleteCar")

                .param("carId","12212")

                .contentType(MediaType.APPLICATION_JSON)

                .accept(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())

                .andReturn();

           

    }
}
