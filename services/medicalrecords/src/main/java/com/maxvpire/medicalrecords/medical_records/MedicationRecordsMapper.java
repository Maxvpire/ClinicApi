package com.maxvpire.medicalrecords.medical_records;

import com.maxvpire.medicalrecords.medical_records.dto.MedicationRecordsWithPrescriptionsResponse;
import com.maxvpire.medicalrecords.prescriptions.Prescriptions;
import com.maxvpire.medicalrecords.prescriptions.dto.PrescriptionResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MedicationRecordsMapper {
    public PrescriptionResponse toPrescriptionResponse(Prescriptions prescription) {
        return new PrescriptionResponse(
                prescription.getId(),
                prescription.getMedicationName(),
                prescription.getDosage(),
                prescription.getFrequency(),
                prescription.getDuration(),
                prescription.getCreatedAt()
        );
    }

    public MedicationRecordsWithPrescriptionsResponse toMedicationRecords(MedicalRecords medicalRecords) {
        List<PrescriptionResponse> prescriptions = new ArrayList<>();
        for (Prescriptions prescription : medicalRecords.getPrescriptions()) {
            PrescriptionResponse prescriptionDto = toPrescriptionResponse(prescription);
            prescriptions.add(prescriptionDto);
        }
        return new MedicationRecordsWithPrescriptionsResponse(
                medicalRecords.getId(),
                medicalRecords.getPatientId(),
                medicalRecords.getDoctorId(),
                medicalRecords.getAppointmentId(),
                medicalRecords.getDiagnosis(),
                medicalRecords.getTreatment(),
                prescriptions,
                medicalRecords.getCreatedAt()
        );
    }
}
