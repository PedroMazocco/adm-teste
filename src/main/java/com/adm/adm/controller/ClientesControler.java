package com.adm.adm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.adm.adm.model.Cliente;
import com.adm.adm.model.TipoPessoa;
import com.adm.adm.service.ClientesService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/cliente")
public class ClientesControler {

    @Autowired
    private ClientesService service;

   @GetMapping("/novo")
    public ModelAndView novo(Cliente cliente) {
        ModelAndView mv = new ModelAndView("CadastroCliente");
        mv.addObject("cliente", cliente);
        mv.addObject("tiposPessoa", TipoPessoa.values());
        return mv;
    }
    
 
    @PostMapping("/nova")
    public ModelAndView cadastrar(@Valid Cliente cliente, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("CadastroCliente");
            mv.addObject("cliente", cliente);
            mv.addObject("tiposPessoa", TipoPessoa.values()); 
            return mv;
        }

        service.salvar(cliente);
        attributes.addFlashAttribute("mensagem", "Cliente cadastrado com sucesso.");
        return new ModelAndView("redirect:/cliente");
    }


    @GetMapping
    public ModelAndView listarClientes() {
        ModelAndView mv = new ModelAndView("ListaClientes");
        mv.addObject("clientes", service.listarTodos());
        mv.addObject("tiposPessoa", TipoPessoa.values());
        return mv;
    }

    @PostMapping("/{codigo}")
    public ModelAndView atualizar(@PathVariable Long codigo, @Valid Cliente cliente, BindingResult result) {
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("CadastroCliente");
            mv.addObject("cliente", cliente);
            mv.addObject("tiposPessoa", TipoPessoa.values()); 
            return mv;
        }

        cliente.setCodigo(codigo);
        service.salvar(cliente);
        return new ModelAndView("redirect:/cliente");
    }


    @PostMapping("/{codigo}/excluir")
    public String excluir(@PathVariable Long codigo) {
        service.deleteById(codigo);
        return "redirect:/cliente";
    }

    @GetMapping("/{codigo}")
    public ModelAndView editar(@PathVariable Long codigo) {
        Cliente cliente = service.buscarPorId(codigo);
        ModelAndView mv = new ModelAndView("CadastroCliente");
        mv.addObject("cliente", cliente);
        mv.addObject("tiposPessoa", TipoPessoa.values());
        return mv;
    }

}
