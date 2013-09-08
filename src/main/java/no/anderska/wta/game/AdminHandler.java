package no.anderska.wta.game;

import no.anderska.wta.dto.CategoriesAnsweredDTO;

import java.util.List;

public interface AdminHandler {
    String restartGame(String password);

    String resetCategories(String password);

    String editCategories(String password, String[] engineNames);

    List<CategoriesAnsweredDTO> categoriesAnswered();

    String toggleLoserBonus(String password);
}
