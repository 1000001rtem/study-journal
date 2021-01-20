import {ComponentFixture, TestBed} from '@angular/core/testing';

import {StudyJournalComponent} from './study-journal.component';

describe('StudyJournalComponent', () => {
  let component: StudyJournalComponent;
  let fixture: ComponentFixture<StudyJournalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [StudyJournalComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StudyJournalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
