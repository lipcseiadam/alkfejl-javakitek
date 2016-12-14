package hu.elte;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    
    List<Recipe> recipes = new ArrayList<>();

    public MainController() {
        recipes.add(new Recipe("recept1", "leírás1"));
        recipes.add(new Recipe("recept2", "leírás2"));
    }
    
    

    @RequestMapping("/")
    public ModelAndView mainPage(Writer responseWriter) throws IOException {
        //responseWriter.write("hello from spring!");
        //return "index";
        ModelAndView result = new ModelAndView("index");
        result.addObject("title", "My page");
        result.addObject("recipes", this.recipes);
        return result;
    }
    
    @RequestMapping(path = "/recipe/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable int id) {
        ModelAndView result = new ModelAndView("edit");
        result.addObject("recipes", recipes.get(id));
        return result;
    }
    
    @RequestMapping(path = "/recipe/{id}", method = RequestMethod.POST)
    public String doEdit(@PathVariable int id, Recipe recipe) {
        recipes.set(id, recipe);
        return "redirect:/";
    }
}
