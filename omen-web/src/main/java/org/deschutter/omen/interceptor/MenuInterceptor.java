package org.deschutter.omen.interceptor;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.deschutter.authentication.user.User;
import org.deschutter.omen.character.CharacterDTO;
import org.deschutter.omen.character.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


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
        MenuItemDTO karakterMenu = new MenuItemDTO("Karakters", null);
        for (CharacterDTO character : characters) {
            karakterMenu.add(new MenuItemDTO(character.getCharacterName(), contextPath + "/epos/character/" + character.getId()));
        }
        List<MenuItemDTO> basicIngameMenu = createBasicIngameMenu(contextPath);
        basicIngameMenu.add(new BlankMenuItem());
        basicIngameMenu.add(karakterMenu);
        return createMenu(contextPath, basicIngameMenu.toArray(new MenuItemDTO[basicIngameMenu.size()]));
    }


    private MenuDTO createMenu(String contextPath, MenuItemDTO[] ingameMenuArray) {
        return new MenuDTO()
                .addMenuItem(createHomeMenuItem(contextPath))
                .addMenuItem(new MenuItemDTO("Ingame", null), ingameMenuArray)
                .addMenuItem( new MenuItemDTO("Outgame",null),createOutgameMenuArray(contextPath))
                .addMenuItem(createContactMenuItem(contextPath));
    }

    private MenuItemDTO[] createOutgameMenuArray(String contextPath) {
		return new MenuItemDTO[] { new MenuItemDTO("Larp", contextPath + "/outgame/larp"),
				new MenuItemDTO("Richtlijnen", contextPath + "/outgame/rules"),
				new MenuItemDTO("Spelershandboek", contextPath + "/outgame/manual"),
				new MenuItemDTO("Forum", contextPath + "/outgame/forum"),
				new MenuItemDTO("Evenementen", contextPath + "/outgame/events") };
    }

    private MenuItemDTO createContactMenuItem(String contextPath) {
        return new MenuItemDTO("Contact", contextPath + "/contact");
    }



    private MenuDTO createNotLoggedInMenu(String contextPath) {
        List<MenuItemDTO> basicIngameMenu = createBasicIngameMenu(contextPath);
        return createMenu(contextPath, basicIngameMenu.toArray(new MenuItemDTO[basicIngameMenu.size()]));
    }

	private List<MenuItemDTO> createBasicIngameMenu(String contextPath) {
		ArrayList<MenuItemDTO> menuItemDTOs = new ArrayList<>();
		menuItemDTOs.add(new MenuItemDTO("Volkeren", contextPath + "/ingame/lineage"));
		menuItemDTOs.add(new MenuItemDTO("Klassen", contextPath + "/ingame/classes"));
		menuItemDTOs.add(new MenuItemDTO("Heimar", contextPath + "/ingame/heimar"));
		menuItemDTOs.add(new MenuItemDTO("Organisaties", contextPath + "/ingame/organisations"));
		menuItemDTOs.add(new MenuItemDTO("Vijanden", contextPath + "/ingame/enemies"));
		menuItemDTOs.add(new MenuItemDTO("Kalender", contextPath + "/ingame/calendar"));
		menuItemDTOs.add(new MenuItemDTO("Taal", contextPath + "/ingame/language"));
		return menuItemDTOs;

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

    private class BlankMenuItem extends MenuItemDTO {
        public BlankMenuItem() {
            super(null,null);
        }
    }
}
