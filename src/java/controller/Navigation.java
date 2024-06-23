package controller;

/**
 *
 * @author Trần Quốc Cường
 */
/**
 * Navigation interface - being used to name the button at file jsp
 */
public interface Navigation {

    // Navigation to Login 
    static final String LOGIN = "Login.jsp";

    // Navigation to Invalid 
    static final String INVALID = "invalid.html";

    // Navigation to Private Page 
    static final String WELCOME = "Welcome.jsp";
    static final String DASHBOARD = "Dashboard.jsp";
    static final String MAIN_DASHBOARD = "MainDashboard.jsp";

    // Navigation to Account Page 
    static final String ADD_ACCOUNT = "AddAccount.jsp";
    static final String LIST_ACCOUNT = "ListAccount.jsp";
    static final String UPDATE_ACCOUNT = "UpdateAccount.jsp";

    // Navigation to Category Page 
    static final String ADD_CATEGORY = "AddCategory.jsp";
    static final String LIST_CATEGORY = "ListCategory.jsp";
    static final String UPDATE_CATEGORY = "UpdateCategory.jsp";

    // Navigation to Product Page 
    static final String ADD_PRODUCT = "AddProduct.jsp";
    static final String LIST_PRODUCT = "ListProduct.jsp";
    static final String UPDATE_PRODUCT = "UpdateProduct.jsp";
    static final String CHANGE_IMG_PRODUCT = "ChangeImgProduct.jsp";

    // Navigation to Product View Page 
    static final String PUBLIC_PRODUCT = "ProductPublic.jsp";

    // Navigation to Footer 
    static final String FOOTER = "footer.html";
}
