package com.pharmalot.pharmalotcontrol.service;

import com.pharmalot.pharmalotcontrol.model.OpBase;
import com.pharmalot.pharmalotcontrol.repository.OpBaseRepository;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Service
public class OpBaseService {

    private final OpBaseRepository opBaseRepository;

    public OpBaseService(OpBaseRepository opBaseRepository) {
        this.opBaseRepository = opBaseRepository;
    }

    public int importarPlanilha(MultipartFile arquivo) throws IOException {

        int quantidadeImportada = 0;

        try (Workbook workbook =
                     WorkbookFactory.create(arquivo.getInputStream())) {

            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);

                if (row == null) {
                    continue;
                }

                // ============================
                // LEITURA DAS 9 COLUNAS
                // ============================

                String op = lerCelula(row.getCell(0));
                String statusOp = lerCelula(row.getCell(1));
                String produto = lerCelula(row.getCell(2));
                String descricaoProduto = lerCelula(row.getCell(3));
                String lote = lerCelula(row.getCell(4));

                LocalDateTime dataHoraCriacao =
                        lerDataHora(row.getCell(5));

                String processoAtual = lerCelula(row.getCell(6));
                String statusProcesso = lerCelula(row.getCell(7));

                Integer quantidadeDesvios =
                        lerInteiro(row.getCell(8));

                if (op.isBlank()) {
                    continue;
                }

                // ============================
                // PROCURA SE A OP JÁ EXISTE
                // ============================

                Optional<OpBase> existente =
                        opBaseRepository.findByOpIgnoreCase(op);

                OpBase registro =
                        existente.orElseGet(OpBase::new);

                // ============================
                // ATUALIZA OS DADOS
                // ============================

                registro.setOp(op);
                registro.setStatusOp(statusOp);
                registro.setProduto(produto);
                registro.setDescricaoProduto(descricaoProduto);
                registro.setLote(lote);
                registro.setDataHoraCriacao(dataHoraCriacao);
                registro.setProcessoAtual(processoAtual);
                registro.setStatusProcesso(statusProcesso);
                registro.setQuantidadeDesvios(quantidadeDesvios);
                registro.setDataImportacao(LocalDateTime.now());

                opBaseRepository.save(registro);

                quantidadeImportada++;
            }
        }

        return quantidadeImportada;
    }

    // ============================
    // BUSCAR OP
    // ============================

    public Optional<OpBase> buscarPorOp(String op) {

        if (op == null || op.isBlank()) {
            return Optional.empty();
        }

        return opBaseRepository
                .findByOpIgnoreCase(op.trim());
    }

    // ============================
    // LER CÉLULA COMO TEXTO
    // ============================

    private String lerCelula(Cell cell) {

        if (cell == null) {
            return "";
        }

        DataFormatter formatter = new DataFormatter();

        return formatter
                .formatCellValue(cell)
                .trim();
    }

    // ============================
    // LER NÚMERO INTEIRO
    // ============================

    private Integer lerInteiro(Cell cell) {

        String valor = lerCelula(cell);

        if (valor.isBlank()) {
            return 0;
        }

        try {

            valor = valor.replace(",", ".");

            return (int) Double.parseDouble(valor);

        } catch (NumberFormatException e) {

            return 0;
        }
    }

    // ============================
    // LER DATA E HORA
    // ============================

    private LocalDateTime lerDataHora(Cell cell) {

        if (cell == null) {
            return null;
        }

        // Data verdadeira do Excel
        if (cell.getCellType() == CellType.NUMERIC
                && DateUtil.isCellDateFormatted(cell)) {

            return cell.getLocalDateTimeCellValue();
        }

        // Caso a data venha como texto
        String valor = lerCelula(cell);

        if (valor.isBlank()) {
            return null;
        }

        DateTimeFormatter[] formatos = {

                DateTimeFormatter.ofPattern(
                        "dd/MM/yyyy HH:mm:ss"),

                DateTimeFormatter.ofPattern(
                        "dd/MM/yyyy HH:mm")
        };

        for (DateTimeFormatter formato : formatos) {

            try {

                return LocalDateTime.parse(
                        valor,
                        formato
                );

            } catch (DateTimeParseException ignored) {
            }
        }

        return null;
    }
}