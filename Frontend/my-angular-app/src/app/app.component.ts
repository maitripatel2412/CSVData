import { Component, OnInit, ViewChild } from '@angular/core';
import { ChartType, ChartOptions } from 'chart.js';
import { ApiService } from './api.service';
import Chart from 'chart.js/auto';
import { responseData } from './ResponseData';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit {
  title = 'my-angular-app';
  data: any[] = [];
  public chart: any;
  response!: responseData;

  public chartLabels = [];
  endYearData: any[] = [];
  relevanceData : any[] =[];
  intensityData : any[] = [];
  likelihoodData : any[] = [];
  cityData : any[] = [];
  startYearData : any[] = [];
  countryData : any[] = [];
  topicData : any[] = [];
  regionData : any[] = [];
  publishedData : any[] = [];

  constructor(private apiService: ApiService) { }

  loopOverList(): void {
     for (let i = 0; i < this.data.length; i++) {
      this.endYearData.push(this.data[i].end_year); 
      this.relevanceData.push(this.data[i].relevance); 
      this.intensityData.push(this.data[i].intensity);
      this.likelihoodData.push(this.data[1].likelihood);
      this.cityData.push(this.data[i].city);
      this.startYearData.push(this.data[i].start_year);
      this.countryData.push(this.data[i].country);
      this.topicData.push(this.data[i].topic);
      this.regionData.push(this.data[i].region);
      this.publishedData.push(this.data[i].published);
    
    }
  }

  ngOnInit() {
    this.apiService.getData().subscribe((result) => {
      this.data = result;
      this.loopOverList();
    });
    
    setTimeout(() => {
      this.createChart();
    }, 10);
    
  }

  createChart() {
    console.log('Fruit data:', this.endYearData);
    this.chart = new Chart("MyChart", {
      type: 'bar', //this denotes tha type of chart

      data: {// values on X-Axis
        labels: ['2017','2018','2019','2020','2021','2022','2023',],
        datasets: [
          {
            label: "End Year",
            data: this.endYearData,
            backgroundColor: 'blue'
          },
          {
            label: " City ",
            data: this.cityData,
            backgroundColor: 'limegreen'
          }
        ]
      },
      options: {
        aspectRatio: 2.5
      }
    });
  }
}

