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
        return new MenuDTO().addMenuItem("Home").addMenuItem("Epos", new String[]{"Algemeen", "Basisdocumenten", "Eternipedia"}).addMenuItem("Iron Fist");
    }

    public class MenuDTO {
        private List<MenuItemDTO> menuItems = new ArrayList<MenuItemDTO>();

        public MenuDTO addMenuItem(String display) {
            menuItems.add(new MenuItemDTO(display));
            return this;
        }

        public MenuDTO addMenuItem(String display, String[] childDisplays) {
            MenuItemDTO menuItem = new MenuItemDTO(display);
            for (String child : childDisplays) {
                menuItem.add(new MenuItemDTO(child));
            }
            menuItems.add(menuItem);
            return this;
        }

        public class MenuItemDTO {
            private List<MenuItemDTO> menuItems = new ArrayList<MenuItemDTO>();
            private String display;

            public MenuItemDTO(String display) {

                this.display = display;
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
        }

        public List<MenuItemDTO> getMenuItems() {
            return menuItems;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
