import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {BehaviorSubject, catchError, Observable, of, tap} from 'rxjs';
@Injectable({
providedIn: 'root'
})
export class FileService {

public filesChanged = new BehaviorSubject<boolean>(false);

baseApiUrl = "http://backend.localdev.me/api/files/"
	
constructor(private http:HttpClient) { }

addFile(file: File): Observable<any> {

	const formData = new FormData();
	formData.append("file", file, file.name);
		
	return this.http.post(this.baseApiUrl, formData).pipe(
		tap(() => this.filesChanged.next(false))
	);
}

getAll(): Observable<any[]> {
	return this.http.get<any[]>(this.baseApiUrl);
}

getFile(uuid: string): Observable<any> {
	return this.http.get<any>(this.baseApiUrl + uuid);
}

deleteFile(uuid: string): Observable<any> {
	return this.http.delete<any>(this.baseApiUrl + uuid).pipe(
		tap(() => this.filesChanged.next(false))
	);
}

}
