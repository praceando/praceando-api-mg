/*
 * Class: CounterService
 * Description: Service for the Counter entity.
 * Author: Camilla Ucci de Menezes
 * Creation Date: 29/10/2024
 * Last Updated: 29/10/2024
 */
package bloomera.praceando.praceandoapimg.service;

import bloomera.praceando.praceandoapimg.model.Counter;
import bloomera.praceando.praceandoapimg.repository.CounterRepository;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CounterService {

    private final CounterRepository counterRepository;

    public CounterService(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    /**
     * @param sequenceName o nome da sequência para a qual o próximo valor é obtido.
     * @return o próximo valor da sequência como um número long.
     */
    @Transactional
    public long getNextSequenceValue(String sequenceName) {
        Counter counter = counterRepository.findById(sequenceName).orElse(new Counter());
        counter.setId(sequenceName);
        counter.setSequenceValue(counter.getSequenceValue() + 1);
        counterRepository.save(counter);
        return counter.getSequenceValue();
    }
}

