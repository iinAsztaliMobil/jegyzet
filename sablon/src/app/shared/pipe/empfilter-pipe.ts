import { Pipe, PipeTransform } from '@angular/core';

const normalize = (value: unknown): string =>
  (value ?? '')
    .toString()
    .trim()
    .toLowerCase()
    .normalize('NFD')
    .replace(/[\u0300-\u036f]/g, '');

@Pipe({
  name: 'empfilter',
})
export class EmpfilterPipe implements PipeTransform {
  transform(employees: any[], filter: { city?: string; name?: string }): any[] {
    if (!employees || !filter) {
      return employees;
    }

    const city = normalize(filter.city);
    const name = normalize(filter.name);

    return employees.filter(
      (emp) =>
        normalize(emp.city).includes(city) &&
        normalize(emp.name).includes(name)
    );
  }
}