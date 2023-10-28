export interface IAllField {
  id?: number;
  allLowercaseRegex?: string;
  nameInteger?: number;
  nameLong?: number;
  nameBigDecimal?: number;
  nameFloat?: number;
  nameDouble?: number;
  nameBoolean?: boolean;
  nameLocalDate?: Date;
  nameZonedDateTime?: Date;
  nameInstant?: Date;
  nameBlobContentType?: string;
  nameBlob?: string;
  nameAnyBlobContentType?: string;
  nameAnyBlob?: string;
  nameImageBlobContentType?: string;
  nameImageBlob?: string;
  nameTextBlob?: string;
}

export class AllField implements IAllField {
  constructor(
    public id?: number,
    public allLowercaseRegex?: string,
    public nameInteger?: number,
    public nameLong?: number,
    public nameBigDecimal?: number,
    public nameFloat?: number,
    public nameDouble?: number,
    public nameBoolean?: boolean,
    public nameLocalDate?: Date,
    public nameZonedDateTime?: Date,
    public nameInstant?: Date,
    public nameBlobContentType?: string,
    public nameBlob?: string,
    public nameAnyBlobContentType?: string,
    public nameAnyBlob?: string,
    public nameImageBlobContentType?: string,
    public nameImageBlob?: string,
    public nameTextBlob?: string
  ) {
    this.nameBoolean = this.nameBoolean ?? false;
  }
}
