import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'hoursFormat',
  standalone: true
})
export class HoursFormatPipe implements PipeTransform {

  transform(value: string): string {
    if(!value) {
      return '00:00';
    }
    const [hours, minutes] = value.split(':');
    return `${hours}:${minutes}`;
  }

}
