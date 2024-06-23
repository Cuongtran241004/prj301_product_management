package controller;

/**
 *
 * @author ACER
 */
public interface Action {

    static final String ADD_ACCOUNT = "AddAccount";
    static final String UPDATE_ACCOUNT = "UpdateAccount";
    static final String LIST_ACCOUNT = "ListAccount";
    static final String DELETE_ACCOUNT = "DeleteAccount";
    static final String ISUSE_UPDATE_ACCOUNT = "IsUseUpdateAccount";
    
    static final String ADD_CATEGORY = "AddCategory";
    static final String UPDATE_CATEGORY = "UpdateCategory";
    static final String LIST_CATEGORY = "ListCategory";
    static final String DELETE_CATEGORY = "DeleteCategory";
    
    static final String ADD_PRODUCT = "AddProduct";
    static final String UPDATE_PRODUCT = "UpdateProduct";
    static final String LIST_PRODUCT = "ListProduct";
    static final String DELETE_PRODUCT = "DeleteProduct";
    static final String CHANGE_IMG_PRODUCT = "ChangeImgProduct";
    
    static final String LOGIN = "Login";
    static final String LOGOUT = "Logout";
}
