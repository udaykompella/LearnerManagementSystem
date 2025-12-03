package org.airtribe.LearnerManagementSystem.service;


import org.airtribe.LearnerManagementSystem.entity.Learner;
import org.airtribe.LearnerManagementSystem.repository.Learnerrepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.*;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class LearnerManagementServiceTest {

//    @Autowired
    @InjectMocks
    private LearnerManagementService learnerManagementService;

//    @MockBean
    @Mock
    private Learnerrepository learnerrepository;

    // Hooks in unit tests
    // beforeAll beforeEach AfterAll AfterEach

    @Test
    public void testCreateLearnerSuccessfully() {
        //Arrange
        Learner learner = new Learner("test","test");
        when(learnerrepository.save(learner)).thenReturn(learner);
        //Act
        Learner createdLearner = learnerManagementService.createLearner(learner);
        //Assert
        Assertions.assertEquals(learner,createdLearner);
        Assertions.assertEquals("test",createdLearner.getLearnerName());
        verify(learnerrepository,times(1)).save(learner);
    }
    @Test
    public void testFetchLearnerByIdSuccessfully() throws LearnerNotFoundException {
        Learner learner = new Learner("test","test");
        when(learnerrepository.findById(1L)).thenReturn(Optional.of(learner));
        Learner fetchedLearner = learnerManagementService.fetchLearnerById(1L);
        Assertions.assertEquals(learner,fetchedLearner);
        verify(learnerrepository,times(1)).findById(1L);
        Assertions.assertEquals("test",fetchedLearner.getLearnerName());
    }

    @Test
    public void testFetchLearnerById_LearnerNotFoundException(){
        when(learnerrepository.findById(1L)).thenReturn(Optional.empty());
        Assertions.assertThrows(LearnerNotFoundException.class, () -> learnerManagementService.fetchLearnerById(1L));
    }
}
