package com.maxvpire.users.users;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maxvpire.users.exception.PasswordNotMatchException;
import com.maxvpire.users.exception.UserNotFoundException;
import com.maxvpire.users.users.dto.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "doctors", groupId = "events-group")
    public void consumeDoctors(String message) {
        try {
            JsonNode root = objectMapper.readTree(message);
            String eventType = root.get("eventType").asText();
            JsonNode payload = root.get("payload");

            switch (eventType) {
                case "DOCTOR_CREATED":
                    KafkaDoctorResponse kafkaDoctorResponse = KafkaDoctorResponse.builder()
                            .id(payload.get("id").asText())
                            .phone(payload.get("phone").asText())
                            .build();
                    createDoctor(kafkaDoctorResponse);
                    log.info("Doctor created!");
                    break;

                case "DOCTOR_DELETED":
                    String id = payload.get("id").asText();
                    deleteDoctor(id);
                    log.info("Doctor deleted!");
                    break;

                default:
                    log.warn("Unknown event type from doctor event: {}", eventType);
            }
        } catch (Exception e) {
            log.error("Failed to process message: {}", message, e);
        }
    }

    private void createDoctor(KafkaDoctorResponse response) {
        User user = User.builder()
                .username(response.getPhone())
                .password(passwordEncoder.encode(response.getPhone()))
                .role(Role.DOCTOR)
                .mainId(response.getId())
                .build();
        userRepository.save(user);
    }

    private void deleteDoctor(String id) {
        User user = userRepository.findUserByMainId(id)
                .orElseThrow(EntityNotFoundException::new);

        userRepository.delete(user);
    }


    @KafkaListener(topics = "patients", groupId = "patients-consumer")
    public void consumePatients(String message) {
        try {
            JsonNode root = objectMapper.readTree(message);
            String eventType = root.get("eventType").asText();
            JsonNode payload = root.get("payload");

            switch (eventType) {
                case "PATIENT_CREATED":
                    KafkaPatientResponse kafkaPatientResponse = KafkaPatientResponse.builder()
                            .id(payload.get("id").asText())
                            .phone(payload.get("phone").asText())
                            .build();
                    createPatient(kafkaPatientResponse);
                    log.info("Patient created!");
                    break;

                case "PATIENT_DELETED":
                    String id = payload.get("id").asText();
                    deletePatient(id);
                    log.info("Patient deleted!");
                    break;

                default:
                    log.warn("Unknown event type from patient event: {}", eventType);
            }
        } catch (Exception e) {
            log.error("Failed to process message: {}", message, e);
        }
    }

    private void createPatient(KafkaPatientResponse response) {
        User user = User.builder()
                .mainId(response.getId())
                .username(response.getPhone())
                .password(passwordEncoder.encode(response.getPhone()))
                .role(Role.PATIENT)
                .build();

        userRepository.save(user);
    }

    private void deletePatient(String id) {
        User user = userRepository.findUserByMainId(id)
                .orElseThrow(EntityNotFoundException::new);
        userRepository.delete(user);
    }

    @KafkaListener(topics = "administrations", groupId = "administrations-consumer")
    public void consumeAdministrations(String message) {
        try {
            JsonNode root = objectMapper.readTree(message);
            String eventType = root.get("eventType").asText();
            JsonNode payload = root.get("payload");

            switch (eventType) {
                case "ADMINISTRATION_CREATED":
                    KafkaAdministrationResponse kafkaAdministrationResponse = KafkaAdministrationResponse.builder()
                            .id(payload.get("id").asText())
                            .phone(payload.get("phone").asText())
                            .build();
                    createAdministration(kafkaAdministrationResponse);
                    log.info("Administration created!");
                    break;

                case "ADMINISTRATION_DELETED":
                    String id = payload.get("id").asText();
                    deleteAdministration(id);
                    log.info("Administration deleted!");
                    break;

                default:
                    log.warn("Unknown event type from administrations event: {}", eventType);
            }
        } catch (Exception e) {
            log.error("Failed to process message: {}", message, e);
        }
    }

    private void createAdministration(KafkaAdministrationResponse response) {
        User user = User.builder()
                .mainId(response.getId())
                .username(response.getPhone())
                .password(passwordEncoder.encode(response.getPhone()))
                .role(Role.ADMINISTRATION)
                .build();

        userRepository.save(user);
    }

    private void deleteAdministration(String id) {
        User user = userRepository.findUserByMainId(id)
                .orElseThrow(EntityNotFoundException::new);
        userRepository.delete(user);
    }


    public List<UserResponse> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    public User findUserById(String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

    public User findUserByUserName(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

    public List<UserResponse> searchUsers(String username) {
        return userRepository.findByUsernameContaining(username)
                .stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    public User findByMainId(String id) {
        return userRepository.findUserByMainId(id)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

    public void updateUsername(String id, String username) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        user.setUsername(username);
        userRepository.save(user);
    }

    public void updatePassword(String id, PasswordCheckRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        boolean matches = passwordEncoder.matches(request.getCurrentPassword(), user.getPassword());

        if (!matches) {
            throw new PasswordNotMatchException("Invalid password!");
        }

        else{
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            userRepository.save(user);
        }

    }
}