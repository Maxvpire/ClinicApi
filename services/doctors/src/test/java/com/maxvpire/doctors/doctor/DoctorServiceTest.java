package com.maxvpire.doctors.doctor;

import com.maxvpire.doctors.doctor.dto.DoctorRequest;
import com.maxvpire.doctors.doctor.dto.DoctorResponse;
import com.maxvpire.doctors.exception.DoctorNotFoundException;
import com.maxvpire.doctors.schedule.ScheduleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private DoctorMapper doctorMapper;

    @Mock
    private ScheduleService scheduleService;

    @InjectMocks
    private DoctorService doctorService;

    private Doctor doctor;
    private DoctorRequest request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        doctor = Doctor.builder()
                .id("doc123")
                .firstname("John")
                .lastname("Doe")
                .email("john.doe@example.com")
                .phone("123456789")
                .gender(Gender.MALE)
                .specialization("Cardiology")
                .dateofbirth(LocalDate.EPOCH)
                .build();

        request = new DoctorRequest(
                "John", "Doe", "john.doe@example.com", "123456789",
                "1980-01-01", Gender.MALE, LocalDate.EPOCH
        );
    }

    @Test
    void testCreateDoctor() {
        when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor);
        String id = doctorService.create(request);
        assertEquals("doc123", id);
    }

    @Test
    void testFindByIdSuccess() {
        when(doctorRepository.findById("doc123")).thenReturn(Optional.of(doctor));
        when(doctorMapper.toDoctorResponse(doctor)).thenReturn(new DoctorResponse(
                "doc123",
                "John",
                "Doe",
                "Cardiology",
                "johndoe@gmail.com",
                "+998763467887",
                "",
                Gender.MALE,
                true,
                false,
                LocalDate.now(),
                List.of(),
                LocalDateTime.now(),
                LocalDateTime.now()

        ));

        DoctorResponse response = doctorService.findById("doc123");
        assertEquals("John", response.firstname());
    }

    @Test
    void testFindByIdNotFound() {
        when(doctorRepository.findById("invalid")).thenReturn(Optional.empty());
        assertThrows(DoctorNotFoundException.class, () -> doctorService.findById("invalid"));
    }

    @Test
    void testUpdateDoctor() {
        when(doctorRepository.findById("doc123")).thenReturn(Optional.of(doctor));
        doctorService.updateDoctor("doc123", new DoctorRequest("Jane", null, null, "987654321", null, null, null));
        verify(doctorRepository).save(any(Doctor.class));
        assertEquals("Jane", doctor.getFirstname());
        assertEquals("123456789", doctor.getPhone());
    }

    @Test
    void testDeleteDoctor() {
        when(doctorRepository.findById("doc123")).thenReturn(Optional.of(doctor));
        doctorService.delete("doc123");
        assertTrue(doctor.isDeleted());
        verify(scheduleService).deleteByDoctorId("doc123");
    }

    @Test
    void testRestoreDoctor() {
        doctor.setDeleted(true);
        when(doctorRepository.findById("doc123")).thenReturn(Optional.of(doctor));
        doctorService.restoreDoctor("doc123");
        assertFalse(doctor.isDeleted());
    }

    @Test
    void testUploadAvatar() {
        when(doctorRepository.findById("doc123")).thenReturn(Optional.of(doctor));
        doctorService.uploadAvatar("doc123", "avatar.png");
        assertEquals("avatar.png", doctor.getAvatar());
    }

    @Test
    void testActivateDoctor() {
        doctor.set_active(false);
        when(doctorRepository.findById("doc123")).thenReturn(Optional.of(doctor));
        doctorService.activeDoctor("doc123");
        assertTrue(doctor.is_active());
    }

    @Test
    void testInactivateDoctor() {
        doctor.set_active(true);
        when(doctorRepository.findById("doc123")).thenReturn(Optional.of(doctor));
        doctorService.inActiveDoctor("doc123");
        assertFalse(doctor.is_active());
        verify(scheduleService).deleteByDoctorId("doc123");
    }
}
