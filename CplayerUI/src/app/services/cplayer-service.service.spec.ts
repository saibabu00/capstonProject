import { TestBed } from '@angular/core/testing';

import { CplayerServiceService } from './cplayer-service.service';

describe('CplayerServiceService', () => {
  let service: CplayerServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CplayerServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
