/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espe.distribuidas.pruebaBatch.tasks;

import ec.edu.espe.distribuidas.pruebaBatch.model.Hola;
import ec.edu.espe.distribuidas.pruebaBatch.model.Test;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 *
 * @author bran-
 */
@Slf4j
public class TestTask implements Tasklet, StepExecutionListener{
    
    private final MongoTemplate mongoTemplate;

    public TestTask(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    
    

    @Override
    public void beforeStep(StepExecution arg0) {
        log.info("iniciando el Test");
    }
    
    @Override
    public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
        Test test = Test.builder()
                .test("Si valio :v")
                .build();
        mongoTemplate.save(test);
        
        Hola hola = Hola.builder()
                .texto("El dinero es dinero Op")
                .build();
        mongoTemplate.save(hola);
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution arg0) {
        return ExitStatus.COMPLETED;
    }
    
}
