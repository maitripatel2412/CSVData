import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  private apiUrl = 'http://localhost:9091/datacsv/all'; // Replace with your API URL

  constructor(private http: HttpClient) {}

  getData(): Observable<any[]> {
    console.log("calling back end ");
    
    return this.http.get<any[]>(this.apiUrl);
  }
}

