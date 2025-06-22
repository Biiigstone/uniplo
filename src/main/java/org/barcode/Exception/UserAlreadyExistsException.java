package org.barcode.Exception;

public class UserAlreadyExistsException extends RuntimeException{


    // 일부 프레임워크에서 리플렉션 사용해서 예외 생성하는 경우가 있음.
    // 즉, 사용자가 직접 말고 프레임워크가 이렇게 동적으로 만들 때가 있으니까
    // 대체로 기본 생성자는 필요하다고 생각하면 된다.
    public UserAlreadyExistsException(){}

    public UserAlreadyExistsException(String message){
        super(message);
    }


}
