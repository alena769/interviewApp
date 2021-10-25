package com.example.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.models.AppUser.AppUser;
import com.example.demo.models.AppUser.AppUserConverter;
import com.example.demo.models.AppUser.AppUserDTO;
import com.example.demo.repositories.AppUser.AppUserRepository;
import com.example.demo.services.AppUser.AppUserService;
import com.example.demo.services.AppUser.AppUserServiceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AppUserController.class})
@ExtendWith(SpringExtension.class)
class AppUserControllerTest {
    @Autowired
    private AppUserController appUserController;

    @MockBean
    private AppUserService appUserService;

    @Test
    void testFindById() {
        AppUser appUser = new AppUser();
        appUser.setLastName("Doe");
        appUser.setEmail("jane.doe@example.org");
        appUser.setPassword("iloveyou");
        appUser.setIsActive(true);
        appUser.setTelephoneNumber("4105551212");
        appUser.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        Date fromResult = Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant());
        appUser.setDateCreated(fromResult);
        appUser.setFirstName("Jane");
        AppUserRepository appUserRepository = mock(AppUserRepository.class);
        when(appUserRepository.findById((Long) any())).thenReturn(Optional.<AppUser>of(appUser));
        AppUserConverter appUserConverter = new AppUserConverter();
        AppUserDTO actualFindByIdResult = (new AppUserController(
                new AppUserServiceImpl(appUserRepository, appUserConverter, new BCryptPasswordEncoder()))).findById(123L);
        assertSame(fromResult, actualFindByIdResult.getDateCreated());
        assertEquals("Doe", actualFindByIdResult.getLastName());
        assertTrue(actualFindByIdResult.getIsActive());
        assertEquals(123L, actualFindByIdResult.getId());
        assertEquals("Jane", actualFindByIdResult.getFirstName());
        verify(appUserRepository).findById((Long) any());
    }

    @Test
    void testFindByIsActive() {
        AppUserRepository appUserRepository = mock(AppUserRepository.class);
        when(appUserRepository.findAllByIsActive((Boolean) any())).thenReturn(new ArrayList<AppUser>());
        AppUserConverter appUserConverter = new AppUserConverter();
        assertTrue((new AppUserController(
                new AppUserServiceImpl(appUserRepository, appUserConverter, new BCryptPasswordEncoder()))).findByIsActive(true)
                .isEmpty());
        verify(appUserRepository).findAllByIsActive((Boolean) any());
    }

    @Test
    void testDelete() throws Exception {
        AppUser appUser = new AppUser();
        appUser.setLastName("Doe");
        appUser.setEmail("jane.doe@example.org");
        appUser.setPassword("iloveyou");
        appUser.setIsActive(true);
        appUser.setTelephoneNumber("4105551212");
        appUser.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        appUser.setDateCreated(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        appUser.setFirstName("Jane");
        doNothing().when(this.appUserService).deleteAppUser((AppUser) any());
        when(this.appUserService.findUserById((Long) any())).thenReturn(appUser);
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/delete");
        MockHttpServletRequestBuilder requestBuilder = deleteResult.param("id", String.valueOf(1L));
        MockMvcBuilders.standaloneSetup(this.appUserController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("redirect:/"));
    }

    @Test
    void testFindByEmail() throws Exception {
        when(this.appUserService.convertAppUsersToDTO((java.util.List<AppUser>) any()))
                .thenReturn(new ArrayList<AppUserDTO>());
        when(this.appUserService.findAllUsers()).thenReturn(new ArrayList<AppUser>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/{email}", "", "Uri Vars");
        MockMvcBuilders.standaloneSetup(this.appUserController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("index"));
    }

    @Test
    void testFindByKeyword() throws Exception {
        when(this.appUserService.convertAppUsersToDTO((java.util.List<AppUser>) any()))
                .thenReturn(new ArrayList<AppUserDTO>());
        when(this.appUserService.findAllUsers()).thenReturn(new ArrayList<AppUser>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/{keyword}", "", "Uri Vars");
        MockMvcBuilders.standaloneSetup(this.appUserController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("index"));
    }

    @Test
    void testHome() throws Exception {
        when(this.appUserService.convertAppUsersToDTO((java.util.List<AppUser>) any()))
                .thenReturn(new ArrayList<AppUserDTO>());
        when(this.appUserService.findAllUsers()).thenReturn(new ArrayList<AppUser>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        MockMvcBuilders.standaloneSetup(this.appUserController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("index"));
    }

    @Test
    void testHome2() throws Exception {
        when(this.appUserService.convertAppUsersToDTO((java.util.List<AppUser>) any()))
                .thenReturn(new ArrayList<AppUserDTO>());
        when(this.appUserService.findAllUsers()).thenReturn(new ArrayList<AppUser>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.appUserController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("index"));
    }

    @Test
    void testListUsersByDateAsc() throws Exception {
        when(this.appUserService.orderAppUsersByDateAsc()).thenReturn(new ArrayList<AppUser>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/datumAsc");
        MockMvcBuilders.standaloneSetup(this.appUserController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("redirect:/"));
    }

    @Test
    void testListUsersByDateAsc2() throws Exception {
        when(this.appUserService.orderAppUsersByDateAsc()).thenReturn(new ArrayList<AppUser>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/datumAsc");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.appUserController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("redirect:/"));
    }

    @Test
    void testListUsersByDateDesc() throws Exception {
        when(this.appUserService.orderAppUsersByDateDesc()).thenReturn(new ArrayList<AppUser>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/datumDesc");
        MockMvcBuilders.standaloneSetup(this.appUserController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("redirect:/"));
    }

    @Test
    void testListUsersByDateDesc2() throws Exception {
        when(this.appUserService.orderAppUsersByDateDesc()).thenReturn(new ArrayList<AppUser>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/datumDesc");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.appUserController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("redirect:/"));
    }

    @Test
    void testUpdate() throws Exception {
        AppUser appUser = new AppUser();
        appUser.setLastName("Doe");
        appUser.setEmail("jane.doe@example.org");
        appUser.setPassword("iloveyou");
        appUser.setIsActive(true);
        appUser.setTelephoneNumber("4105551212");
        appUser.setId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        appUser.setDateCreated(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        appUser.setFirstName("Jane");
        doNothing().when(this.appUserService).updateAppUser((AppUser) any());
        when(this.appUserService.findUserById((Long) any())).thenReturn(appUser);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/update");
        MockMvcBuilders.standaloneSetup(this.appUserController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("redirect:/"));
    }
}

