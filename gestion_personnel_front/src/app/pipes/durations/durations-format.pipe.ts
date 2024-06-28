import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'durationsFormat',
  standalone: true
})
export class DurationsFormatPipe implements PipeTransform {

  transform(value: string | undefined): string {
    if (!value) {
      return '00:00';
    }

    // Use a regular expression to extract hours and minutes
    const durationRegex = /PT(?:(\d+)H)?(?:(\d+)M)?/;
    const matches = value.match(durationRegex);

    if (!matches) {
      return value; // Return the original value if it doesn't match the expected format
    }

    const hours = matches[1] ? parseInt(matches[1], 10) : 0;
    const minutes = matches[2] ? parseInt(matches[2], 10) : 0;

    // Format hours and minutes as "H:MM"
    return `${hours}:${minutes.toString().padStart(2, '0')}`;
  }

}
