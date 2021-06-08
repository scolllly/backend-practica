package com.backend.practica.mysql.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import com.backend.practica.mysql.model.ClienteMySql;
import com.backend.practica.mysql.repository.ClienteMySqlRepository;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.MockedConstruction.MockInitializer;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

// @RunWith(SpringJUnit4ClassRunner.class)
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ClienteMySqlServiceImplTest {

   @InjectMocks // @Autowired  
    ClienteMySqlServiceImpl service;

    @Mock // @MockBean
    ClienteMySqlRepository repoMock;

    @Mock
    ClienteMySql mysql;

    private static final Integer ID = 1;
    private static final String NOMBRES = "ALEXIS";
    private static final String APELLIDOS = "PERALTA";
    private static final String TIPO = "DNI";
    private static final String NUMERO = "72498694";
    private static final short EDAD = 27;

    @Before
    public void setup()
    {
      mysql = new ClienteMySql();
      mysql.setId(ID);
      mysql.setNombres(NOMBRES);
      mysql.setApellidos(APELLIDOS);
      mysql.setTipoIdentificacion(TIPO);
      mysql.setNmroIdentificacion(NUMERO);
      mysql.setEdad(EDAD);

      
    }

    @Test
    public void buscarClienteTest()
    {
      Mockito.when(repoMock.findById(ID)).thenReturn(Optional.of(mysql));

      service.buscarCliente(ID);
      verify(repoMock).findById(ID);
       
    }
    
}
