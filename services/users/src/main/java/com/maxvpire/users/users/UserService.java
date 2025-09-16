package com.maxvpire.users.users;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxvpire.users.users.dto.KafkaDoctorResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @KafkaListener(topics = "doctors", groupId = "doctors-consumer", containerFactory = "kafkaListenerContainerFactory")
    public String listen(ConsumerRecord<String, Object> record) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            String json = record.value().toString();
            KafkaDoctorResponse response = mapper.readValue(json, KafkaDoctorResponse.class);
            return create(response);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private String create(KafkaDoctorResponse response) {
        User user = User.builder()
                .username(response.getPhone())
                .password(passwordEncoder.encode(response.getPhone()))
                .mainId(response.getId())
                .role(Role.DOCTOR)
                .build();
        return userRepository.save(user).getId();
    }

    @KafkaListener(topics = "delete-doctors", groupId = "doctors-consumer", containerFactory = "kafkaListenerContainerFactory")
    public void deleteTopic(ConsumerRecord<String, String> record) {
        try{
            User user = userRepository.findUserByMainId(record.value().replaceAll("\"", ""))
                    .orElseThrow(() -> new EntityNotFoundException("User not found!"));

            userRepository.delete(user);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}