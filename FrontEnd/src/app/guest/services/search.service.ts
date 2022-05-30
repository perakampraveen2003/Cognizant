import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SearchRequestModel } from '../models/SearchRequestModel';
import { SearchResponseModel } from '../models/SearchResponseModel';

@Injectable({
  providedIn: 'root'
})
export class SearchService {
   
  constructor(public httpClient:HttpClient) { }

  getDistinctFromPlaces() {
      return this.httpClient.get<string[]>("http://localhost:8989/api/v1.0/flight/fromPlaces");
  }
  
  getDistinctToPlaces() {
    return this.httpClient.get<string[]>("http://localhost:8989/api/v1.0/flight/toPlaces");
  }

  SearchAll(searchRequestModel: SearchRequestModel) {
       return this.httpClient.post<SearchResponseModel[]>("http://localhost:8989/api/v1.0/flight/search",searchRequestModel);
  }

  
}

