package org.deschutter.omen.interceptor;

import org.deschutter.authentication.user.User;
import org.deschutter.omen.character.CharacterDTO;
import org.deschutter.omen.character.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


public class MenuInterceptor implements HandlerInterceptor {
    @Autowired
    CharacterService characterService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            User user = ((User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal());
            modelAndView.addObject("menu", createLoggedInMenu(request.getContextPath(), characterService.getCharactersForUserID(user.getUserId())));
        } else {
            modelAndView.addObject("menu", createNotLoggedInMenu(request.getContextPath()));
        }
    }


    private MenuDTO createLoggedInMenu(String contextPath, List<CharacterDTO> characters) {
        List<MenuItemDTO> eposMenu = new ArrayList<>();
        MenuItemDTO karakters = new MenuItemDTO("Karakters", null);
        eposMenu.add(karakters);
        for (CharacterDTO character : characters) {
            karakters.add(new MenuItemDTO(character.getCharacterName(), contextPath + "/epos/character/" + character.getId()));
        }
        eposMenu.add(new MenuItemDTO(null, null));

        return createMenu(contextPath, eposMenu.toArray(new MenuItemDTO[eposMenu.size()]));
    }


    private MenuDTO createMenu(String contextPath, MenuItemDTO[] eposMenuArray) {
        return new MenuDTO()
                .addMenuItem(createHomeMenuItem(contextPath))
                .addMenuItem(new MenuItemDTO("Epos", null), eposMenuArray);
    }

    private MenuDTO createNotLoggedInMenu(String contextPath) {
        MenuItemDTO[] eposMenuArray = {
                new MenuItemDTO("Algemeen", contextPath + "/epos/algemeen")};
        return createMenu(contextPath, eposMenuArray);
    }

    private MenuItemDTO createHomeMenuItem(String contextPath) {
        return new MenuItemDTO("Home", contextPath + "/index");
    }

    public class MenuDTO {
        private final List<MenuItemDTO> menuItems = new ArrayList<>();

        public MenuDTO addMenuItem(MenuItemDTO display) {
            menuItems.add(display);
            return this;
        }

        public MenuDTO addMenuItem(MenuItemDTO display, MenuItemDTO[] childDisplays) {
            for (MenuItemDTO child : childDisplays) {
                display.add(child);
            }
            menuItems.add(display);
            return this;
        }


        public List<MenuItemDTO> getMenuItems() {
            return menuItems;
        }
    }

    public class MenuItemDTO {
        private final List<MenuItemDTO> menuItems = new ArrayList<>();
        private final String display;
        private final String url;

        public MenuItemDTO(String display, String url) {

            this.display = display;
            this.url = url;
        }

        public String getDisplay() {
            return display;
        }

        public void add(MenuItemDTO menuItem) {
            menuItems.add(menuItem);
        }

        public List<MenuItemDTO> getMenuItems() {
            return menuItems;
        }

        public String getUrl() {
            return url;
        }
    }


    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
