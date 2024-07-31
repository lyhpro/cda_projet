import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResetPwdFormComponent } from './reset-pwd-form.component';

describe('ResetPwdFormComponent', () => {
  let component: ResetPwdFormComponent;
  let fixture: ComponentFixture<ResetPwdFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ResetPwdFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ResetPwdFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
