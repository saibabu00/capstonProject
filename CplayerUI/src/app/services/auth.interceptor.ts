import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { AuthServiceService } from "./auth-service.service";


@Injectable()
export class AuthInterceptor implements HttpInterceptor{
constructor(private loginService:AuthServiceService) {
    
}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        // throw new Error("Method not implemented.");

        let newReq = req.headers;
        const token = this.loginService.getToken();

        console.log('INTERCEPTOR');

        if(token!=null)
        {
            newReq = newReq.append('authtoken', token);
        }
        const authReq = req.clone({headers: newReq});

        return next.handle(authReq)

    }
}