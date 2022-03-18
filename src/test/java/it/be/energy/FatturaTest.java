package it.be.energy;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import it.be.energy.repository.FatturaRepository;
import it.be.energy.services.FatturaService;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class FatturaTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	FatturaService fatturaService;
	
	@Mock
	FatturaRepository fatturaRepository;

	@Test
	@WithMockUser
	final void testGetAll() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/fattura/getAll")).andDo(print()).andExpect(status().isAccepted());	
	}
	
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	final void testDeleteById() throws Exception {
		this.mockMvc.perform(delete("http://localhost:8080/fattura/delete/1")).andDo(print()).andExpect(status().isOk());	
	}
	@Test
	@WithMockUser
	final void testfindById() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/fattura/find_id/1")).andDo(print()).andExpect(status().isAccepted());	
	}
	
	@Test
	@WithMockUser(username = "user", password = "user", roles = "USER")
	final void testUser() throws Exception {
		this.mockMvc.perform(delete("http://localhost:8080/fattura/delete/1")).andDo(print()).andExpect(status().isForbidden());	
	}

}
