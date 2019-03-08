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
    private String id;

    private String title;

    private String description;

    private List<String> likes;

    private List<String> ingredients;

    private List<RecipeComment> comments;

    public String getId() {
        return id;
    }

    public void setId(String _id) {
        this.id = _id;
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
        if (this.likes == null) {
            this.likes = new ArrayList<>();
        }
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
        if (this.comments == null) {
            this.comments = new ArrayList<>();
        }
        this.comments.add(recipeComment);
    }

    public void updateComment(RecipeComment recipeComment) {
        RecipeComment recipeCommentToChange = this.comments.stream()
                .filter(rc -> recipeComment.getId().equals(rc.getId()))
                .findFirst().orElseThrow(() -> new RuntimeException("Comentario nao encontrado"));

        int index = this.comments.indexOf(recipeCommentToChange);
        recipeCommentToChange.setComment(recipeComment.getComment());
        this.comments.set(index, recipeCommentToChange);
    }

    public void removeComment(String recipeCommentId) {
        RecipeComment recipeComment = this.comments.stream()
                .filter(rc -> recipeCommentId.equals(rc.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Comentario nao encontrado"));

        this.comments.remove(recipeComment);
    }

}
