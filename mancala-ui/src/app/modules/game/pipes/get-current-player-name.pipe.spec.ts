import { GetCurrentPlayerNamePipe } from './get-current-player-name.pipe';

describe('GetCurrentPlayerNamePipe', () => {
  it('create an instance', () => {
    const pipe = new GetCurrentPlayerNamePipe();
    expect(pipe).toBeTruthy();
  });
});
