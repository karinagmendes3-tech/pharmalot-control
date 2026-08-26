package com.pharmalot.pharmalotcontrol.service;

import com.pharmalot.pharmalotcontrol.model.OpBase;
import com.pharmalot.pharmalotcontrol.repository.OpBaseRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OpBaseService {

    private final OpBaseRepository opBaseRepository;

    public OpBaseService(OpBaseRepository opBaseRepository) {
        this.opBaseRepository = opBaseRepository;
    }

    public int importarPlanilha(MultipartFile arquivo) throws IOException {

        int quantidadeImportada = 0;

        try (Workbook workbook = WorkbookFactory.create(arquivo.getInputStream())) {

            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);

                if (row == null) {
                    continue;
                }

                String op = lerCelula(row.getCell(0));
                String lote = lerCelula(row.getCell(1));
                String produto = lerCelula(row.getCell(2));
                String etapa = lerCelula(row.getCell(3));

                if (op.isBlank()) {
                    continue;
                }

                Optional<OpBase> existente =
                        opBaseRepository.findByOpIgnoreCase(op);

                OpBase registro = existente.orElseGet(OpBase::new);

                registro.setOp(op);
                registro.setLote(lote);
                registro.setProduto(produto);
                registro.setEtapaSistema(etapa);
                registro.setDataImportacao(LocalDateTime.now());

                opBaseRepository.save(registro);

                quantidadeImportada++;
            }
        }

        return quantidadeImportada;
    }

    public Optional<OpBase> buscarPorOp(String op) {

        if (op.isBlank()) {
            return Optional.empty();
        }

        return opBaseRepository.findByOpIgnoreCase(op.trim());
    }

    private String lerCelula(Cell cell) {

        if (cell == null) {
            return "";
        }

        DataFormatter formatter = new DataFormatter();

        return formatter.formatCellValue(cell).trim();
    }
}