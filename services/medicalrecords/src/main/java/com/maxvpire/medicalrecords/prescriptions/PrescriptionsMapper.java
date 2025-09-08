package com.maxvpire.medicalrecords.prescriptions;

import com.maxvpire.medicalrecords.medical_records.MedicalRecords;
import com.maxvpire.medicalrecords.medical_records.dto.MedicationRecordsResponse;
import com.maxvpire.medicalrecords.prescriptions.dto.PrescriptionWithMedicationRecordsResponse;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionsMapper {
    public MedicationRecordsResponse toMedicationRecordsResponse(MedicalRecords medicalRecords) {
        return new MedicationRecordsResponse(
                medicalRecords.getId(),
                medicalRecords.getPatientId(),
                medicalRecords.getDoctorId(),
                medicalRecords.getAppointmentId(),
                medicalRecords.getDiagnosis(),
                medicalRecords.getTreatment(),
                medicalRecords.getCreatedAt()
        );
    }

    public PrescriptionWithMedicationRecordsResponse toPrescriptionWithMedicationRecordsResponse(Prescriptions prescription) {
        return new PrescriptionWithMedicationRecordsResponse(
                prescription.getId(),
                toMedicationRecordsResponse(prescription.getMedicalRecords()),
                prescription.getMedicationName(),
                prescription.getDosage(),
                prescription.getFrequency(),
                prescription.getDuration(),
                prescription.getCreatedAt()
        );
    }
}
