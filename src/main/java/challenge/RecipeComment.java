package challenge;

import org.springframework.data.annotation.Id;

/**
 * Classe para mapear o coment�rio da receita no MongoDB
 *
 */
public class RecipeComment {

    @Id
    private String _id;

    private String comment;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
