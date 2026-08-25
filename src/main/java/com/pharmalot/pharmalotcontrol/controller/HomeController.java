package com.pharmalot.pharmalotcontrol.controller;

import com.pharmalot.pharmalotcontrol.model.EtapaProducao;
import com.pharmalot.pharmalotcontrol.service.EtapaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    private final EtapaService etapaService;

    public HomeController(EtapaService etapaService) {
        this.etapaService = etapaService;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }
    @GetMapping("/producao")public String producao(){return "producao";}
    @GetMapping("/equipamento")public String equipamento(){return "equipamentos";}
    @GetMapping("/logbook")public String logbook(Model model){List<Map<String, String>>areas=List.of(Map.of("nome","Pesagem","descricao","Box de pesagem","url","pesagem"),
            Map.of("nome","Manipulação","descricao","Misturagranulação e preparação","url","manipulacao"),
            Map.of("nome","Compressão","descricao","Compressão de Comprimidos","url","compressao"),
            Map.of("nome","Revestimento","descricao","Revestimento de comprimidos","url","revestimento"),
            Map.of("nome","Acondicionamento", "descricao","Embalagem e acondicionamento","url","acondicionamento"));
    model.addAttribute("areas",areas);
    return "logbook";
    }
    @GetMapping("/logbook/{area}")
    public String equipamentosPorArea(@PathVariable String area,Model model) {model.addAttribute("area",area);
        if(area.equals("manipulacao")){
            model.addAttribute("equipamentos",List.of(
                    Map.of("nome","Diosna 02","tag","HOM504001(3)","status","Ativo"),
                    Map.of("nome","Misturador de Bins","tag","MIS504007","status","ativo")));
        }else {
            model.addAttribute("aquipamentos", List.of());
        }
            return "logbook-equipamentos";
        }
    @GetMapping("/historico")public String historico(){return "historico";}
    @GetMapping("/relatorios")public String administracao(){return "administracao";}

    @GetMapping("/buscar")
    public String buscarOp(@RequestParam String op,Model model) {
        List<EtapaProducao> etapas = etapaService.buscarPorOp(op);
        model.addAttribute("etapas", etapas);
        model.addAttribute("op", op);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}