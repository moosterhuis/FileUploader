import { Component, OnInit } from '@angular/core';
import { FileService } from '../file.service';

@Component({
  selector: 'app-file-overview',
  templateUrl: './file-overview.component.html',
  styleUrls: ['./file-overview.component.css']
})
export class FileOverviewComponent implements OnInit {

  files!: any[];
  loading: boolean = false;

  constructor(private fileService: FileService) {
    this.fileService.filesChanged.subscribe(() => {
      this.getFiles();
    });
  }

  ngOnInit(): void {
    this.getFiles();
  }

  getFiles(): void {
    this.loading = !this.loading;
    this.fileService.getAll().subscribe((files) => {
      this.files = files;
      this.loading = !this.loading;
    })
  }

  getFile(uuid: string): any {
    this.loading = !this.loading;
    return this.fileService.getFile(uuid).subscribe(() => this.loading = !this.loading);
  }

  deleteFile(uuid: string) {
    this.loading = !this.loading;
    this.fileService.deleteFile(uuid).subscribe(() => this.loading = !this.loading);
  }

}
