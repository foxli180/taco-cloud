package tacocloud.web;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tacocloud.ingredient;
import tacocloud.Taco;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

    @GetMapping
    public String showDesignForm(Model model) {
        List<ingredient> ingredients = Arrays.asList(
                new ingredient("PLTO", "Flour Tortilla", ingredient.Type.WRAP),
                new ingredient("COTO", "Corn Tortilla", ingredient.Type.WRAP),
                new ingredient("CRBF", "Ground Beef", ingredient.Type.PROTEIN),
                new ingredient("CARN", "Carnitas", ingredient.Type.PROTEIN),
                new ingredient("TMTO", "Diced Tomatoes", ingredient.Type.VEGGIES),
                new ingredient("LETC", "Lettuce", ingredient.Type.VEGGIES),
                new ingredient("CHED", "Cheddar", ingredient.Type.CHEESE),
                new ingredient("JACK", "Monterrey Jack", ingredient.Type.CHEESE),
                new ingredient("SLSA", "Salsa", ingredient.Type.SAUCE),
                new ingredient("SRCR", "Sour Cream", ingredient.Type.SAUCE)
        );
        ingredient.Type[] types = ingredient.Type.values();
        for(ingredient.Type type: types){
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }

        model.addAttribute("design", new Taco());
        return "design";
    }

    private List<ingredient> filterByType(
            List<ingredient> ingredients, ingredient.Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

}
