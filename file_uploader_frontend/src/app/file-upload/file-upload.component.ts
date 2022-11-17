import { Component, OnInit } from '@angular/core';
import { FileService } from '../file.service';

@Component({
	selector: 'app-file-upload',
	templateUrl: './file-upload.component.html',
	styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent implements OnInit {

	loading: boolean = false;
	file: File | undefined;

	constructor(private fileService: FileService) { }

	ngOnInit(): void {
	}

	onSelectFile(event: any) {
		this.file = event.target.files[0];	
	}

	onUploadFile() {
		this.loading = true;
		if(this.file !== undefined) {
			this.fileService.addFile(this.file).subscribe(
			(event: any) => {
				if (typeof (event) === 'object') {
				}
			}
			);
		}
		this.loading = false;
	}
}
