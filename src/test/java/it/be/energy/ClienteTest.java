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
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import it.be.energy.repository.ClienteRepository;
import it.be.energy.services.ClienteService;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ClienteTest {
	@Autowired
	private MockMvc mockMvc;

	@Mock
	ClienteRepository clienteRepo;

	@Mock
	ClienteService clienteService;



	@Test
	@WithMockUser
	final void testGetAll() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080//cliente/trovatutti")).andDo(print()).andExpect(status().isOk());	
	}
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	final void testPost() throws Exception {
		String body = "{\r\n"
				+ "  \"tipo\": \"TO\",\r\n"
				+ "  \"ragioneSociale\": \"pippo\",\r\n"
				+ "  \"partitaIva\": \"string\",\r\n"
				+ "  \"email\": \"pippo@gmail.com\",\r\n"
				+ "  \"dataInserimento\": \"2020-02-12\",\r\n"
				+ "  \"dataUltimoContatto\": \"2020-04-15\",\r\n"
				+ "  \"pec\": \"string@pec.it\",\r\n"
				+ "  \"telefono\": \"12345\",\r\n"
				+ "  \"nomeContatto\": \"pippo\",\r\n"
				+ "  \"cognomeContatto\": \"pippo\",\r\n"
				+ "  \"telefonoContatto\": \"12345\",\r\n"
				+ "  \"indirizzoSedeLegale\": {\r\n"
				+ "    \"id\": 1\r\n"
				+ "    },\r\n"
				+ "  \"indirizzoSedeOperativa\": {\r\n"
				+ "    \"id\": 1\r\n"
				+ "  },\r\n"
				+ "  \"fatture\": [\r\n"
				+ "    {\r\n"
				+ "      \"anno\": 2020,\r\n"
				+ "      \"data\": \"2020-02-12\",\r\n"
				+ "      \"importo\": 10000,\r\n"
				+ "      \"numero\": 6,\r\n"
				+ "      \"stato\": \"pagato\"\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "}";
		
		 MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/cliente/inserisci")
	    			.contentType(MediaType.APPLICATION_JSON)
	    			.content(body))
	    			.andExpect(status().isCreated()).andReturn();
	}
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	final void testPut() throws Exception {
		String body ="{\r\n"
				+ "  \"tipo\": \"PA\",\r\n"
				+ "  \"id\":\"2\",\r\n"
				+ "  \"ragioneSociale\": \"Google\",\r\n"
				+ "  \"partitaIva\": \"string\",\r\n"
				+ "  \"email\": \"google@gmail.com\",\r\n"
				+ "  \"dataInserimento\": \"2022-03-14\",\r\n"
				+ "  \"dataUltimoContatto\": \"2022-03-14\",\r\n"
				+ "  \"pec\": \"Microdoft\",\r\n"
				+ "  \"telefono\": \"222555888\",\r\n"
				+ "  \"nomeContatto\": \"Toad\",\r\n"
				+ "  \"cognomeContatto\": \"string\",\r\n"
				+ "  \"telefonoContatto\": \"222555888\",\r\n"
				+ "  \"indirizzoSedeLegale\": {\r\n"
				+ "    \"id\": 2\r\n"
				+ "    },\r\n"
				+ "  \"indirizzoSedeOperativa\": {\r\n"
				+ "    \"id\": 2\r\n"
				+ "  },\r\n"
				+ "  \"fatture\": [\r\n"
				+ "    {\r\n"
				+ "      \"anno\": 2020,\r\n"
				+ "      \"data\": \"2020-02-13\",\r\n"
				+ "      \"importo\": 400,\r\n"
				+ "      \"numero\": 6,\r\n"
				+ "      \"stato\": \"pagato\"\r\n"
				+ "    }\r\n"
				+ "  ]\r\n"
				+ "}";
		
		 MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put("/cliente/aggiorna/2")
	    			.contentType(MediaType.APPLICATION_JSON)
	    			.content(body))
	    			.andExpect(status().isCreated()).andReturn();
	}
	@Test
	@WithMockUser(username = "admin", password = "admin", roles = "ADMIN")
	final void testDeleteById() throws Exception {
		this.mockMvc.perform(delete("http://localhost:8080/cliente/cancella/1")).andDo(print()).andExpect(status().isOk());	
	}
	@Test
	@WithMockUser
	final void testfindById() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/cliente/trovaperid/1")).andDo(print()).andExpect(status().isOk());	
	}
	
	@Test
	@WithMockUser
	final void trovaByRagioneSocialeContaining() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/cliente/trovaperragione/Epico")).andDo(print()).andExpect(status().isOk());	
	}
	@Test
	@WithMockUser
	final void findByOrderByDataUltimoContatto() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/cliente/trovaperdataultimocontatto")).andDo(print()).andExpect(status().isOk());	
	}
	@Test
	@WithMockUser
	final void findByOrderByDataInserimento() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/cliente/trovaperdatainserimento")).andDo(print()).andExpect(status().isOk());	
	}
	@Test
	@WithMockUser
	final void findAllByOrderByFatturatoAnnuale() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/cliente/trovaperfatturato")).andDo(print()).andExpect(status().isOk());	
	}
	


	

}
