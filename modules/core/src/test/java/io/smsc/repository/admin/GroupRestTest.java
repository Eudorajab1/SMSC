package io.smsc.repository.admin;

import io.smsc.AbstractTest;
import io.smsc.model.admin.Group;
import org.junit.Test;
import org.springframework.security.test.context.support.WithMockUser;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WithMockUser(username = "admin", roles = {"POWER_ADMIN_USER"})
public class GroupRestTest extends AbstractTest {

    @Test
    public void testGetSingleGroup() throws Exception {
        mockMvc.perform(get("/rest/repository/groups/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("ADMIN_USER_ADMIN")));
    }

    @Test
    public void testGroupNotFound() throws Exception {
        mockMvc.perform(get("/rest/repository/groups/999")
                .contentType("application/json;charset=UTF-8"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllGroups() throws Exception {
        mockMvc.perform(get("/rest/repository/groups"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.groups", hasSize(20)))
                .andExpect(jsonPath("$._embedded.groups[0].name", is("ADMIN_USER_ADMIN")))
                .andExpect(jsonPath("$._embedded.groups[19].name", is("ADMIN_USER_ROLE_READ_ONLY")));

    }

    @Test
    public void testCreateGroup() throws Exception {
        Group group = new Group();
        group.setName("GROUP_ALL_RIGHTS");
        this.mockMvc.perform(post("/rest/repository/groups")
                .contentType("application/json;charset=UTF-8")
                .content(json(group)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/rest/repository/groups/1"));
        mockMvc.perform(get("/rest/repository/groups/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateUser() throws Exception {
        Group group = new Group();
        group.setId(2L);
        group.setName("GROUP_ALL_RIGHTS");
        mockMvc.perform(put("/rest/repository/groups/2")
                .contentType("application/json;charset=UTF-8")
                .content(json(group)))
                .andExpect(status().isOk());
        mockMvc.perform(get("/rest/repository/groups/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("GROUP_ALL_RIGHTS")));
    }
}