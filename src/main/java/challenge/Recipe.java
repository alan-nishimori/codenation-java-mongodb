package challenge;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe para mapear a receita no MongoDB
 *
 */
@Document(collection = "recipe")
public class Recipe {

    @Id
    private String _id;

    private String title;

    private String description;

    private List<String> likes = new ArrayList<>();

    private List<String> ingredients;

    private List<RecipeComment> comments = new ArrayList<>();

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getLikes() {
        return likes;
    }

    public void addLike(String id) {
        this.likes.add(id);
    }

    public void removeLike(String id) {
        this.likes.remove(id);
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<RecipeComment> getComments() {
        return comments;
    }

    public void addComment(RecipeComment recipeComment) {
        this.comments.add(recipeComment);
    }

    public void updateComment(RecipeComment recipeComment) {
        RecipeComment recipeCommentToChange = this.comments.stream()
                .filter(rc -> recipeComment.get_id().equals(rc.get_id()))
                .findFirst().orElseThrow(() -> new RuntimeException("Comentario nao encontrado"));

        int index = this.comments.indexOf(recipeCommentToChange);
        recipeCommentToChange.setComment(recipeComment.getComment());
        this.comments.set(index, recipeCommentToChange);
    }

    public void removeComment(String recipeCommentId) {
        RecipeComment recipeComment = this.comments.stream()
                .filter(rc -> recipeCommentId.equals(rc.get_id()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Comentario nao encontrado"));

        this.comments.remove(recipeComment);
    }
}
