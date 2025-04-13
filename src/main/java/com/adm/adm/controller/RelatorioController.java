package com.adm.adm.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.adm.adm.model.Cliente;
import com.adm.adm.model.Produto;
import com.adm.adm.service.ClientesService;
import com.adm.adm.service.ProdutoService;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("/relatorios")
public class RelatorioController {

    @Autowired
    private ClientesService clientesService;
    
    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/clientes")
    public void relatorioClientes(HttpServletResponse response) {
        try {
            InputStream jasperStream = getClass().getResourceAsStream("/relatorios/relatorio-cliente.jasper");
            List<Cliente> lista = clientesService.listarTodos();
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);

            Map<String, Object> parametros = new HashMap<>();

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, parametros, dataSource);

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=relatorio-clientes.pdf");

            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao gerar relatório: " + e.getMessage());
        }
    }
    

    @GetMapping("/produtos")
    public void relatorioProdutos(HttpServletResponse response) {
        try {
            InputStream jasperStream = getClass().getResourceAsStream("/relatorios/relatorio-produto.jasper");
            List<Produto> lista = produtoService.listarTodos();
            System.out.println("Quantidade de produtos: " + lista.size());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
            
            Map<String, Object> parametros = new HashMap<>();

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperStream, parametros, dataSource);

            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=relatorio-produtos.pdf");

            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao gerar relatório: " + e.getMessage());
        }
    }
}
