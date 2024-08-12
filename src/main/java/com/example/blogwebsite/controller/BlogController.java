package com.example.blogwebsite.controller;

import com.example.blogwebsite.models.Recipe;
import com.example.blogwebsite.models.User;
import com.example.blogwebsite.repository.RecipeRepository;
import com.example.blogwebsite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URL;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String loginPostAdd (@RequestParam String name, @RequestParam String email, @RequestParam String password, Model model){
        User user = new User(name, email, password);
        userRepository.save(user);

        return "redirect:/logged-user";
    }

    @GetMapping("/logged-user")
    public String loggedUser(Model model) {
        return "logged-user";
    }


    @GetMapping("/recipe-page")
    public String recipePage(Model model) {
        Iterable<Recipe> recipes = recipeRepository.findAll();
        model.addAttribute("recipes", recipes);
        return "recipe-page";
    }

    @GetMapping("/add-recipe")
    public String addRecipe(Model model) {
        return "add-recipe";
    }

    @PostMapping("/add-recipe")
    public String PostRecipe(@RequestParam URL url, @RequestParam String title, @RequestParam Integer cookTime, @RequestParam String ingredients,@RequestParam String text, Model model) {
        Recipe recipe = new Recipe(url, title, cookTime, ingredients, text);
        recipeRepository.save(recipe);
        return "redirect:/recipe-page";
    }
    /*
    @GetMapping("/recipe-description")
    public String loginSuccess(Model model) {
        Iterable<Recipe> recipes = recipeRepository.findAll();
        model.addAttribute("recipes", recipes);
        return "recipe-description";
    }

     */

    @GetMapping("/recipe-description/{recipeId}")
    public String recipeDescription(@PathVariable Long recipeId, Model model) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);

       // if (optionalRecipe.isPresent()) {
            Recipe recipe = optionalRecipe.get();
            model.addAttribute("recipe", recipe);
            return "recipe-description";
       /* }else {
            return "recipe-not-found";
        }*/
}


    @GetMapping("/login-old-user")
    public String showLoginForm() {
        return "login-old-user";
    }



}
