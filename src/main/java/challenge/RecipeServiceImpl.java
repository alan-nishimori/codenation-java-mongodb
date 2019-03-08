package challenge;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    private RecipeRepository repository;

    @Override
    public Recipe save(Recipe recipe) {
        recipe.setId(new ObjectId().toHexString());
        return repository.save(recipe);
    }

    @Override
    public void update(String id, Recipe recipe) {
        Optional<Recipe> recipeEntity = repository.findById(id);
        if (recipeEntity.isPresent()) {
            recipeEntity.get().setTitle(recipe.getTitle());
            recipeEntity.get().setDescription(recipe.getDescription());
            recipeEntity.get().setIngredients(recipe.getIngredients());
            repository.save(recipeEntity.get());
        }
    }

    @Override
    public void delete(String id) {
        repository.deleteById(id);
    }

    @Override
    public Recipe get(String id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Recipe not found"));
    }

    @Override
    public List<Recipe> listByIngredient(String ingredient) {
        List<Recipe> recipes = repository.findAllByIngredientsOrderByTitle(ingredient);
        return recipes.isEmpty() ? null : recipes;
    }

    @Override
    public List<Recipe> search(String search) {
        return repository.findAllByDescriptionIgnoreCaseLikeOrTitleIgnoreCaseLike(search, search);
    }

    @Override
    public void like(String id, String userId) {
        Optional<Recipe> recipeEntity = repository.findById(id);
        if (recipeEntity.isPresent()) {
            recipeEntity.get().addLike(userId);
            repository.save(recipeEntity.get());
        }
    }

    @Override
    public void unlike(String id, String userId) {
        Optional<Recipe> recipeEntity = repository.findById(id);
        if (recipeEntity.isPresent()) {
            recipeEntity.get().removeLike(userId);
            repository.save(recipeEntity.get());
        }
    }

    @Override
    public RecipeComment addComment(String id, RecipeComment comment) {
        comment.setId(new ObjectId().toHexString());
        Optional<Recipe> recipeEntity = repository.findById(id);
        if (recipeEntity.isPresent()) {
            recipeEntity.get().addComment(comment);
            repository.save(recipeEntity.get());
        }
        return comment;
    }

    @Override
    public void updateComment(String id, String commentId, RecipeComment comment) {
        Optional<Recipe> recipeEntity = repository.findById(id);
        if (recipeEntity.isPresent()) {
            comment.setId(commentId);
            recipeEntity.get().updateComment(comment);
            repository.save(recipeEntity.get());
        }
    }

    @Override
    public void deleteComment(String id, String commentId) {
        Optional<Recipe> recipeEntity = repository.findById(id);
        if (recipeEntity.isPresent()) {
            recipeEntity.get().removeComment(commentId);
            repository.save(recipeEntity.get());
        }
    }

}
