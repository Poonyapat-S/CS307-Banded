import { Timestamp } from "rxjs";

export class Post {
  constructor(
  public userName: string,
  public postTitle: string,
  public postText: string,
  public topicName: string,
  public isAnon: boolean,
  public postTime: Date,
  ) {}
}
