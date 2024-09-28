package com.mogharib.student.management.system.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPCell;
import com.mogharib.student.management.system.entity.Course;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class CourseSchedulePdfService {

    public ByteArrayOutputStream generateCourseSchedulePdf(List<Course> courses) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();

            document.add(new Paragraph("Course Schedule"));
            document.add(new Paragraph(" "));

            PdfPTable table = new PdfPTable(5);
            table.addCell(new PdfPCell(new Paragraph("Course ID")));
            table.addCell(new PdfPCell(new Paragraph("Course Name")));
            table.addCell(new PdfPCell(new Paragraph("Course Code")));
            table.addCell(new PdfPCell(new Paragraph("Instructor Name")));
            table.addCell(new PdfPCell(new Paragraph("Course Price")));

            for (Course course : courses) {
                table.addCell(new PdfPCell(new Paragraph(course.getId().toString())));
                table.addCell(new PdfPCell(new Paragraph(course.getCourseName())));
                table.addCell(new PdfPCell(new Paragraph(course.getCourseCode())));
                table.addCell(new PdfPCell(new Paragraph(course.getInstructorName())));
                table.addCell(new PdfPCell(new Paragraph(String.valueOf(course.getCoursePrice()))));
            }

            document.add(table);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }

        return outputStream;
    }
}
