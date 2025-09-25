package thymeleaf.constant;

public class Constant {
	
	//User
	public static final String ATTR_USER = "user";
	public static final String USER_LIST = "/WEB-INF/views/user/list.jsp";
	//public static final String USER_ADD = "/WEB-INF/views/user/add.jsp";
	public static final String USER_EDIT = "/WEB-INF/views/user/edit.jsp";

	//Category
	public static final String CATEGORY_LIST = "/WEB-INF/views/category/list.jsp";
	public static final String CATEGORY_EDIT = "/WEB-INF/views/category/edit.jsp";
	public static final String CATEGORY_CREATE = "/WEB-INF/views/category/create.jsp";
    
	//Video
	public static final String VIDEO_LIST = "/WEB-INF/views/video/list.jsp";
	public static final String VIDEO_EDIT = "/WEB-INF/views/video/edit.jsp";
	public static final String VIDEO_CREATE = "/WEB-INF/views/video/create.jsp";
	public static final String VIDEO_DETAIL = "/WEB-INF/views/video/detail.jsp";
	
	//Common
	public static final String HOME = "/WEB-INF/views/home.jsp";
	
	// Cookie name for remember-me
    public static final String COOKIE_REMEMBER = "rememberUser";

    // Roles
    public static final int ROLE_ADMIN = 1;
    public static final int ROLE_MANAGER = 2;
    public static final int ROLE_USER = 3;

    // Messages (for errors)
    public static final String MSG_USERNAME_EXISTS = "Username already exists";
    public static final String MSG_EMAIL_EXISTS = "Email already exists";
    public static final String MSG_INVALID_LOGIN = "Invalid username or password";
    public static final String MSG_REQUIRED = "Please fill in all required fields";
}
