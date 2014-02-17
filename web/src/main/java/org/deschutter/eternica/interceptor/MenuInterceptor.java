package org.deschutter.eternica.interceptor;

import org.deschutter.authentication.user.User;
import org.deschutter.eternica.character.CharacterService;
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
        }
        modelAndView.addObject("menu", createMenu());

    }

    private MenuDTO createMenu() {
        return new MenuDTO().addMenuItem(new MenuItemDTO("Home", "/index")).addMenuItem(new MenuItemDTO("Epos", null), new MenuItemDTO[]{new MenuItemDTO("Algemeen", "/epos/algemeen"), new MenuItemDTO("Basisdocumenten", "/epos/basisdocumenten"), new MenuItemDTO("Eternipedia", "http://eternipedia.eternica.com")}).addMenuItem(new MenuItemDTO("Iron Fist", "http://ironfist.eternica.com"));
    }

    public class MenuDTO {
        private List<MenuItemDTO> menuItems = new ArrayList<>();

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
        private List<MenuItemDTO> menuItems = new ArrayList<MenuItemDTO>();
        private String display;
        private String url;

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
