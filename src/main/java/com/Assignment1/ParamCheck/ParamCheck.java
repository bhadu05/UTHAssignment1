package com.Assignment1.ParamCheck;
import com.Assignment1.ExceptionHandler.CustomException;
import com.Assignment1.Models.AuthorizationBody;
import com.Assignment1.Models.User;

public class ParamCheck {
    public static boolean checkMobileNumber(String mobileNumber)
    {

        if(mobileNumber.matches("^[0-9]{10}$"))
        {
            if((mobileNumber.charAt(0)!='0'))
                return true;
            else
                throw new CustomException("601","Mobile number can't start with 0.");
        }
        else
            throw new CustomException("601","Either mobile number length is not 10 or it contains an invalid character.");


    }


   public static boolean checkFirstName(String firstName)
    {
        if((firstName != null))
        {
            if(!firstName.equals(""))
            {
                if(firstName.matches("^[a-zA-Z]*$"))
                {
                    return true;
                }
                else
                    throw new CustomException("601'","First Name contains characters other than alphabets.");
            }
            else
                throw new CustomException("601","First name can't be void.");
        }
        else throw new CustomException("601","First name can't be null.");
    }


   public static boolean checkLastName(String lastName)
    {
        if((lastName == null) || (lastName.matches("^[a-zA-Z]*$")))
            return true;
        else
            throw new CustomException("601","Last name contains characters other than alphabets.");
    }


    public static boolean checkBalance(Double balance)
    {
        if(balance<=0)
        throw new CustomException("601","Balance can't be zero or negative.");
        else
            return true;
    }


    public static boolean checkUserID(Long userID)
    {
        if(userID!=null)
            return true;
        else
            throw new CustomException("601","User ID can't be null.");
    }


    public static boolean checkEmailID(String emailID)
    {
        if(emailID!=null)
        {
            if(!emailID.equals(""))
            {
                if(emailID.matches("^[a-zA-z0-9+._-]+@[a-zA-z+.]+[.][a-zA-z+.]+[a-zA-Z]+$"))
                {
                    return true;
                }
                else
                    throw new CustomException("601","Invalid email ID.");
            }
            else
                throw new CustomException("601","Email ID can't be void.");
        }
        else
            throw new CustomException("601","Email ID can't be null.");
    }


    public static boolean checkUserName(String userName)
    {
        if(userName!=null)
        {
            if(userName!="")
            {
                return true;
            }
            else
                throw new CustomException("601","User name can't be void.");
        }
        else
            throw new CustomException("601","User name can't be null.");


    }

    public static boolean checkTransactionID(String transactionID)
    {
        if(transactionID!=null)
        {
            if(transactionID!="")
            {
                return true;
            }
            else
                throw new CustomException("601","Transaction ID can't be void.");
        }
        else
            throw new CustomException("601","Transaction ID can't be null.");

    }
    public static boolean checkUser(User user)
    {
         if(checkUserName(user.getUserName()) && checkFirstName(user.getFirstName()) && checkLastName(user.getLastName()) && checkMobileNumber(user.getMobileNumber()) && checkEmailID(user.getEmailID()) && checkPassword(user.getPassword()))
         {
             if(user.getAddress1()!=null&&user.getAddress1()!="")
             {
                 return true;
             }
             else
                 throw new CustomException("601","Address1 can't be void or null.");
         }
         return false;

    }

    public static boolean checkPassword(String password)
    {
        if(password!=null)
        {
            if(password!="")
            {
                if(password.matches("^[A-Za-z0-9\\s$&+?@#.^*()%!_-]{8,16}+$"))
                {
                    return true;
                }
                else
                    throw new CustomException("601","Password doesn't matches it's specified format.");
            }
            else
                throw new CustomException("601","Password can't be empty");
        }
        else
            throw new CustomException("601","Password can't be null");
    }
    public static boolean checkAuthorizationBody(AuthorizationBody authorizationBody) {
        if(checkUserName(authorizationBody.getUserName()) && checkPassword(authorizationBody.getPassword()))
            return true;
        return false;
    }
}


